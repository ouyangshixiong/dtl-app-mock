/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteInfoUpdateComponent } from 'app/entities/site-info/site-info-update.component';
import { SiteInfoService } from 'app/entities/site-info/site-info.service';
import { SiteInfo } from 'app/shared/model/site-info.model';

describe('Component Tests', () => {
    describe('SiteInfo Management Update Component', () => {
        let comp: SiteInfoUpdateComponent;
        let fixture: ComponentFixture<SiteInfoUpdateComponent>;
        let service: SiteInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteInfoUpdateComponent]
            })
                .overrideTemplate(SiteInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SiteInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SiteInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SiteInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.siteInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new SiteInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.siteInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
