/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { UserLevelComponent } from 'app/entities/user-level/user-level.component';
import { UserLevelService } from 'app/entities/user-level/user-level.service';
import { UserLevel } from 'app/shared/model/user-level.model';

describe('Component Tests', () => {
    describe('UserLevel Management Component', () => {
        let comp: UserLevelComponent;
        let fixture: ComponentFixture<UserLevelComponent>;
        let service: UserLevelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [UserLevelComponent],
                providers: []
            })
                .overrideTemplate(UserLevelComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserLevelComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserLevelService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new UserLevel(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.userLevels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
