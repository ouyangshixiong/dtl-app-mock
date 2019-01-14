/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreUserComponent } from 'app/entities/store-user/store-user.component';
import { StoreUserService } from 'app/entities/store-user/store-user.service';
import { StoreUser } from 'app/shared/model/store-user.model';

describe('Component Tests', () => {
    describe('StoreUser Management Component', () => {
        let comp: StoreUserComponent;
        let fixture: ComponentFixture<StoreUserComponent>;
        let service: StoreUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreUserComponent],
                providers: []
            })
                .overrideTemplate(StoreUserComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StoreUserComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreUserService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StoreUser(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.storeUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
